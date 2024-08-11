package com.figueroa.githubclonesampleapp.data

import androidx.compose.ui.graphics.Color

data class WorkItem(val color: Color, val icon: Int, val label: String, val onClick: () -> Unit = {})