import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginPageComponent } from './login-page/login-page.component';
import { AuthLayoutComponent } from './shared/layouts/auth-layout/auth-layout.component';
import { SiteLayoutComponent } from './shared/layouts/site-layout/site-layout.component';
import { RegisterPageComponent } from './register-page/register-page.component';
import { ProfilePageComponent } from './profile-page/profile-page.component';
import { MessagePageComponent } from './message-page/message-page.component';
import { GamesPageComponent } from './games-page/games-page.component';
import { GameFormComponent } from './games-page/game-form/game-form.component';

const routes: Routes = [
  {
    path:'', component: AuthLayoutComponent, children: [
      {path: '', redirectTo:'/login', pathMatch:'full'},
      {path: 'login', component: LoginPageComponent},
      {path: 'register', component: RegisterPageComponent}
    ]
  },
    {
    path:'', component: SiteLayoutComponent, children: [
      {
        path:'profile-page', component: ProfilePageComponent
      },
      {
        path: 'message', component: MessagePageComponent
      },
      {
        path: 'games', component: GamesPageComponent
      },
      {
        path: 'games/new', component: GameFormComponent
      },
      {
        path: 'games/:id', component: GameFormComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
