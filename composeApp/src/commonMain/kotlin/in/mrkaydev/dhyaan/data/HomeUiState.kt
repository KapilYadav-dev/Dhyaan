package `in`.mrkaydev.dhyaan.data

sealed class HomeUiState {
    object Nothing:HomeUiState()
    object Loading:HomeUiState()
    object Success:HomeUiState()
}
