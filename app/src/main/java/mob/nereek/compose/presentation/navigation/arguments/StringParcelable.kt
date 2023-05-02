package mob.nereek.compose.presentation.navigation.arguments

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

// This was just an experiment with savedStateHandle.
// It's a Parcelable data class used to send data to the next screen.
@Parcelize
data class StringParcelable(val value: String) : Parcelable