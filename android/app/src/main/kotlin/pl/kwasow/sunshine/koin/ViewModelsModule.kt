package pl.kwasow.sunshine.koin

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import pl.kwasow.sunshine.ui.AppViewModel
import pl.kwasow.sunshine.ui.screens.home.HomeScreenViewModel
import pl.kwasow.sunshine.ui.screens.login.LoginScreenViewModel
import pl.kwasow.sunshine.ui.screens.modules.location.LocationModuleViewModel
import pl.kwasow.sunshine.ui.screens.modules.memories.MemoriesModuleViewModel
import pl.kwasow.sunshine.ui.screens.modules.missingyou.MissingYouModuleViewModel
import pl.kwasow.sunshine.ui.screens.modules.music.MusicModuleViewModel
import pl.kwasow.sunshine.ui.screens.modules.whishlist.WishlistModuleViewModel
import pl.kwasow.sunshine.ui.screens.settings.SettingsScreenViewModel
import pl.kwasow.sunshine.ui.widgets.daystogether.DaysTogetherViewModel
import pl.kwasow.sunshine.ui.widgets.memories.MemoriesWidgetViewModel
import pl.kwasow.sunshine.ui.widgets.music.PlaybackWidgetViewModel

val viewModelsModule =
    module {
        viewModel {
            AppViewModel(get())
        }

        viewModel {
            LoginScreenViewModel(get(), get())
        }

        viewModel {
            HomeScreenViewModel(get(), get(), get(), get())
        }

        viewModel {
            LocationModuleViewModel(get(), get())
        }

        viewModel {
            MemoriesModuleViewModel(get())
        }

        viewModel {
            MissingYouModuleViewModel(get(), get())
        }

        viewModel {
            MusicModuleViewModel(get(), get())
        }

        viewModel {
            WishlistModuleViewModel(get(), get(), get())
        }

        viewModel {
            DaysTogetherViewModel()
        }

        viewModel {
            MemoriesWidgetViewModel(get())
        }

        viewModel {
            PlaybackWidgetViewModel(get())
        }

        viewModel {
            SettingsScreenViewModel(get(), get(), get(), get(), get())
        }
    }
