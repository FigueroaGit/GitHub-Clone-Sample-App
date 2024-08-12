package com.figueroa.githubclonesampleapp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.figueroa.githubclonesampleapp.R
import com.figueroa.githubclonesampleapp.ui.theme.Gray30

@Composable
fun WorkSection(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    icon: Int,
    foregroundColor: Color,
    label: String,
    hasInfo: Boolean = true,
    info: String? = null,
    onClick: () -> Unit
) {
    Row(
        modifier
            .clickable { onClick() }
            .padding(vertical = 12.dp, horizontal = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Surface(shape = RoundedCornerShape(4.dp), color = backgroundColor) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "Icon $label",
                modifier
                    .padding(8.dp)
                    .size(16.dp),
                tint = foregroundColor
            )
        }
        Text(text = label, modifier = Modifier.weight(1F))
        if (!hasInfo) {
            Box {}
        } else if (hasInfo && info != null) {
            Text(text = info, color = Gray30)
        } else {
            Icon(
                imageVector = Icons.Rounded.KeyboardArrowDown,
                contentDescription = stringResource(id = R.string.icon_arrow_down_description),
                tint = Gray30
            )
        }
    }
}

@Composable
@Preview
fun WorkSectionPreview() {
    WorkSection(
        backgroundColor = Color.Green,
        icon = R.drawable.ic_issues,
        foregroundColor = Color.White,
        label = "Issues",
        hasInfo = true,
        info = 12.toString(),
        onClick = {})
}