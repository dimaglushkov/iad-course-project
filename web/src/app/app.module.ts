import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { AuthLayoutComponent } from './shared/layouts/auth-layout/auth-layout.component';
import { SiteLayoutComponent } from './shared/layouts/site-layout/site-layout.component';
import { RegisterPageComponent } from './register-page/register-page.component';
import { GamesPageComponent } from './games-page/games-page.component';
import { MessagePageComponent } from './message-page/message-page.component';
import { UserPageComponent } from './user-page/user-page.component';
import { WelcomePageComponent } from './welcome-page/welcome-page.component';
import { ErrorPageComponent } from './error-page/error-page.component';
import { MessageNewComponent } from './message-new/message-new.component';
import { MessageOutComponent } from './message-out/message-out.component';
import { MessageInComponent } from './message-in/message-in.component';
import { UserInfoComponent } from './user-info/user-info.component';
import { UserFriendsComponent } from './user-friends/user-friends.component';
import { UserGamesComponent } from './user-games/user-games.component';
import { UserReviewsComponent } from './user-reviews/user-reviews.component';
import { SettingsComponent } from './settings/settings.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginPageComponent,
    AuthLayoutComponent,
    SiteLayoutComponent,
    RegisterPageComponent,
    GamesPageComponent,
    MessagePageComponent,
    UserPageComponent,
    WelcomePageComponent,
    ErrorPageComponent,
    MessageNewComponent,
    MessageOutComponent,
    MessageInComponent,
    UserInfoComponent,
    UserFriendsComponent,
    UserGamesComponent,
    UserReviewsComponent,
    SettingsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
