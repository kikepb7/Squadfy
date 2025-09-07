package com.kikepb.squadfy.ui.common.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kikepb.squadfy.ui.common.theme.BackgroundAppColor

@Composable
fun RegisterStepScaffold(
    onBack: () -> Unit,
    content: @Composable ColumnScope.() -> Unit,
    bottomContent: @Composable () -> Unit
) {
    Scaffold(
        topBar = {
            CustomTopBar(
                modifier = Modifier,
                leftIcon = Icons.AutoMirrored.Filled.ArrowBack,
                onLeftClick = onBack
            )
        },
        containerColor = BackgroundAppColor
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f, fill = true)
            ) {
                content()
            }

            bottomContent()
        }
    }
}