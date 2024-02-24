package de.lehmansn.adaptivedesign.injection

import de.lehmansn.adaptivedesign.data.ProductMapper
import de.lehmansn.adaptivedesign.data.ProductRepository
import de.lehmansn.adaptivedesign.domain.GetProductsUseCase
import de.lehmansn.adaptivedesign.network.ShopRemoteSource
import de.lehmansn.adaptivedesign.network.fakeShopClient
import de.lehmansn.adaptivedesign.ui.home.HomeViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    // Network
    single { fakeShopClient }
    singleOf(::ShopRemoteSource)

    factoryOf(::ProductRepository)
    factoryOf(::ProductMapper)
    factoryOf(::GetProductsUseCase)

    factoryOf(::HomeViewModel)
}