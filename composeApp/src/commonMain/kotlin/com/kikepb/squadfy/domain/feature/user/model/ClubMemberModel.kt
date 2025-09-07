package com.kikepb.squadfy.domain.feature.user.model

import com.kikepb.squadfy.domain.feature.player.model.PlayerModel

data class ClubMemberModel(
    val user: UserModel,
    val player: PlayerModel? = null,
    val inviteCode: String? = null
)