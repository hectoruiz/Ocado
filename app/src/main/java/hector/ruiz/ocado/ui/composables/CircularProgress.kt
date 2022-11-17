package hector.ruiz.ocado.ui.composables

import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import hector.ruiz.ocado.R
import hector.ruiz.ocado.ui.theme.OcadoTheme

const val TAG_LOADING_PROGRESS = "loadProgress"

@Composable
fun CircularProgress(modifier: Modifier) {

    OcadoTheme {
        CircularProgressIndicator(
            modifier = modifier
                .testTag(TAG_LOADING_PROGRESS)
                .size(dimensionResource(id = R.dimen.progress_size)))
    }
}