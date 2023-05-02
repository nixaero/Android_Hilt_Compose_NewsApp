package mob.nereek.compose.common

// A sealed class to represent the different states of a network operation
sealed class Resource<T> {
    // Represents a successful operation with its result data
    class Success<T>(val data: T) : Resource<T>()
    // Represents a failed operation with an error message
    class Error<T>(val message: String) : Resource<T>()
    // Represents a currently ongoing operation
    class Loading<T> : Resource<T>()

    // A function to transform the data type of the operation result
    fun <R> map(transform: (T) -> R): Resource<R> {
        // Maps the current Resource state to a new one with the transformed data type
        return when (this) {
            is Success -> Success(transform(data))
            is Error -> Error(message)
            is Loading -> Loading()
        }
    }
}
