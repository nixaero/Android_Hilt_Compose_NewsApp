package mob.nereek.compose.learning101

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.system.measureTimeMillis

class FlowTest {

    @Test
    fun testMutableStateFlowDuplicateValuesAreFilteredOut() = runBlocking {
        // Déclaration d'un MutableStateFlow avec une valeur initiale de 0
        val mutableCounter = MutableStateFlow(0)

        // Liste pour stocker les valeurs collectées
        val collectedValues = mutableListOf<Int>()

        var i: Int = 0

        val job = launch {
            // Collecte les valeurs du MutableStateFlow dans collectedValues
            mutableCounter.collect { value ->
                collectedValues.add(value)
                i++
            }
        }

        delay(100)
        mutableCounter.value = 1
        delay(100)
        mutableCounter.value = 2
        delay(100)
        mutableCounter.value = 2
        delay(100)
        mutableCounter.value = 3
        delay(100)
        mutableCounter.value = 3
        delay(100)
        mutableCounter.value = 4

        delay(1000)
        job.cancel()

        delay(1000)
        for (value in collectedValues) {
            println("Value: $value")
        }

        delay(1000)
        assertEquals(listOf(0, 1, 2, 3, 4), collectedValues)
    }

    @Test
    fun testFlowWithDuplicates() = runBlocking {
        // Liste mutable pour stocker les valeurs collectées
        val mutableList = mutableListOf<Int>()

        // Liste de listes pour stocker les valeurs collectées avec des doublons regroupés
        val flowList = mutableListOf<List<Int>>()

        // Définition d'un Flow émettant des valeurs
        val flow: Flow<Int> = flow {
            delay(100)
            emit(1)
            delay(100)
            emit(2)
            delay(100)
            emit(2)
            delay(100)
            emit(3)
            delay(100)
            emit(3)
            delay(100)
            emit(4)
        }

        // Collecte les valeurs du Flow
        flow.collect {
            mutableList.add(it)

            val lastFlowList = flowList.lastOrNull()
            if (lastFlowList == null || lastFlowList.last() != it) {
                // Si la dernière liste de flowList est nulle ou la dernière valeur est différente de it, on ajoute une nouvelle liste
                flowList.add(listOf(it))
            } else {
                // Sinon, on ajoute la valeur it à la dernière liste existante
                val newList = lastFlowList.toMutableList()
                newList.add(it)
                flowList[flowList.lastIndex] = newList
            }
        }

        delay(1000)
        println(mutableList) // Affiche les valeurs collectées
        println(flowList) // Affiche les listes de valeurs avec des doublons regroupés

        // Vérifie les résultats attendus
        assertEquals(listOf(1, 2, 2, 3, 3, 4), mutableList)
        //assertEquals(listOf(listOf(1), listOf(2), listOf(2), listOf(3), listOf(3), listOf(4)), flowList)
    }

    @Test
    fun testSharedFlow() = runBlocking {
        // Création d'un MutableSharedFlow avec le type Int
        val sharedFlow = MutableSharedFlow<Int>()

        val job1 = launch {
            // Collecte les valeurs du SharedFlow dans la première coroutine
            sharedFlow.collect {
                println("Job 1: $it")
            }
        }

        val job2 = launch {
            // Collecte les valeurs du SharedFlow dans la deuxième coroutine
            sharedFlow.collect {
                println("Job 2: $it")
            }
        }

        // Émet une valeur de 1 dans le SharedFlow
        sharedFlow.emit(1)
        delay(1000)

        // Émet une valeur de 2 dans le SharedFlow
        sharedFlow.emit(2)
        delay(1000)

        // Annulation du premier job
        job1.cancel()
        delay(1000)

        // Émet une valeur de 3 dans le SharedFlow
        sharedFlow.emit(3)
        delay(1000)

        // Annulation du deuxième job
        job2.cancel()
        delay(1000)

        // Émet une valeur de 4 dans le SharedFlow
        sharedFlow.emit(4)
        delay(1000)

        delay(1000)

        // Attendre un peu pour laisser le temps aux jobs d'être annulés avant de terminer le test
    }


    @Test
    fun testFlow() = runBlocking {
        val flow = flow {
            emit("Value 1")
            emit("Value 1")
            emit("Value 3")
        }

        val result = mutableListOf<String>()
        flow.collect {
            result.add(it)
        }

        assertEquals(listOf("Value 1", "Value 1", "Value 3"), result)
    }


    @Test
    fun testCoroutineScopes() = runBlocking {
        println("Start")

        var time = measureTimeMillis {
            // Lancement d'une coroutine avec GlobalScope (utilisation déconseillée)
            GlobalScope.launch(Dispatchers.IO) {
                //delay(1000L)
                println("Coroutine 1 finished")
            }
        }

        println("Coroutine 1 End Time = $time Millis")

        time = measureTimeMillis {
            // Lancement d'une coroutine avec le scope du test (runBlocking)
            launch(Dispatchers.IO) {
                //delay(1000L)
                println("Coroutine 2 finished")
            }
        }

        println("Coroutine 2 End Time = $time Millis")

        time = measureTimeMillis {
            // Lancement d'une coroutine avec le scope du test (runBlocking)
            launch {
                //delay(1000L)
                println("Coroutine 3 finished")
            }
        }

        println("Coroutine 3 End Time = $time Millis")
    }


    @Test
    fun testAsync() = runBlocking {
        val deferred1 = async {
            "Hello"
        }
        val deferred2 = async {
            "World"
        }
        val combinedResult = "${deferred1.await()}, ${deferred2.await()}!"
        println(combinedResult)
    }


}