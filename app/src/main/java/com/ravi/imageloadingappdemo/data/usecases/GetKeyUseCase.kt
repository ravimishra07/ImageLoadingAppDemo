package com.ravi.imageloadingappdemo.data.usecases

import com.ravi.imageloadingappdemo.data.AccessKeyRepository
import javax.inject.Inject

class GetKeyUseCase @Inject constructor(
    private val repository: AccessKeyRepository
) {
    operator fun invoke() = repository.getAccessKey()
}