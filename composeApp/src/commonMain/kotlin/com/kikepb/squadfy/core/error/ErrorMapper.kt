package com.kikepb.squadfy.core.error

import com.kikepb.squadfy.domain.common.FailureModel

expect fun Throwable.toFailureModel(): FailureModel