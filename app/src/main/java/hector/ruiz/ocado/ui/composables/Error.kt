package hector.ruiz.ocado.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import hector.ruiz.ocado.R
import hector.ruiz.ocado.ui.theme.OcadoTheme

@Preview
@Composable
fun ErrorData(onRetrieveClicked: () -> Unit) {

    OcadoTheme {
        Image(painter = painterResource(id = R.drawable.ic_error),
            contentDescription = stringResource(id = R.string.error_description),
            modifier = Modifier.size(
                dimensionResource(id = R.dimen.error_size)))
        Text(
            style = MaterialTheme.typography.h1,
            text = stringResource(id = R.string.error_network),
            modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.small_padding_parent))
        )
        Button(onClick = onRetrieveClicked) {
            Text(text = stringResource(id = R.string.retrieve_data))
        }
    }
}