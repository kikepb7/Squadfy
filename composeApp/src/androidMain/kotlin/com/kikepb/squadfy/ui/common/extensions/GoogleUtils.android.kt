package com.kikepb.squadfy.ui.common.extensions

import android.content.Intent
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.kikepb.squadfy.R.string as RString


@Composable
actual fun rememberGoogleSignIn(onTokenReceived: (String) -> Unit): () -> Unit {
    val context = LocalContext.current

    val gso = remember {
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(RString.default_web_client_id))
            .requestEmail()
            .build()
    }
    val googleSignInClient: GoogleSignInClient = remember { GoogleSignIn.getClient(context, gso) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)
            val idToken = account.idToken
            if (idToken != null) {
                onTokenReceived(idToken)
            }
        } catch (e: ApiException) {
            Log.e("GoogleSignIn", "Google sign in failed", e)
        }
    }

    return {
        val intent: Intent = googleSignInClient.signInIntent
        launcher.launch(intent)
    }
}