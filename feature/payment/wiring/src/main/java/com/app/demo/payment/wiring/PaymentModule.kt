package com.app.demo.payment.wiring

import com.app.demo.payment.data.repository.PaymentRepositoryImpl
import com.app.demo.payment.domain.repository.PaymentRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface PaymentModule {
    @Binds
    fun bindPaymentRepository(impl: PaymentRepositoryImpl): PaymentRepository
}