package com.figueroa.githubclonesampleapp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.figueroa.githubclonesampleapp.R
import com.figueroa.githubclonesampleapp.ui.theme.Gray30

@Composable
fun TitleSection(
    modifier: Modifier = Modifier,
    icon: Int? = null,
    label: String,
    buttonText: String? = null,
    isHome: Boolean = false
) {
    Surface {
        Row(
            modifier
                .padding(vertical = 8.dp, horizontal = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (!isHome && icon != null && buttonText != null) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = "Icon $label",
                    modifier.size(16.dp),
                    tint = Gray30
                )
                Text(
                    text = label,
                    color = Gray30,
                    fontSize = 16.sp,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Left,
                )
                TextButton(
                    onClick = { /*TODO*/ },
                    modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(text = buttonText,modifier.weight(1F), color = Color.Blue, textAlign = TextAlign.End)
                }
            } else {
                Text(
                    text = label,
                    fontSize = 16.sp,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Left,
                )
            }

        }
    }
}

@Composable
@Preview
fun TitleSectionPreview() {
    TitleSection(
        label = "My Work",
        icon = R.drawable.ic_branch,
        buttonText = "CHANGE BRANCH",
        isHome = false
    )
}