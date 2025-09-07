package com.kikepb.squadfy.ui.common.extensions

import androidx.compose.runtime.Composable

@Composable
expect fun rememberGoogleSignIn(onTokenReceived: (String) -> Unit): () -> Unit

//@Composable
//fun rememberGoogleClient(context: Context): GoogleSignInClient {
//    return remember {
//        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken(context.getString(RString.default_web_client_id))
//            .requestEmail()
//            .build()
//        GoogleSignIn.getClient(context, gso)
//    }
//}
//
//@Composable
//fun rememberGoogleSignInLauncher(
//    onTokenReceived: (String) -> Unit
//): ManagedActivityResultLauncher<Intent, ActivityResult> {
//    return rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
//        try {
//            val account = task.getResult(ApiException::class.java)
//            val idToken = account.idToken
//            if (idToken != null) {
//                onTokenReceived(idToken)
//            }
//        } catch (e: ApiException) {
//            Log.e("GoogleLogin", "Google sign in failed", e)
//        }
//    }
//}