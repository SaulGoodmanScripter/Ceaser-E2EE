package org.ceaser.e2ee.ui.details

import org.ceaser.e2ee.domain.Details
import org.ceaser.e2ee.util.formatDate

data class DetailsUiState(
    val detail: Details = Details(),
    val offline: Boolean = false
) {
    val formattedUserSince = formatDate(detail.userSince)
}