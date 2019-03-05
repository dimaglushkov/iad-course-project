import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginPageComponent } from './login-page/login-page.component';
import { AuthLayoutComponent } from './shared/layouts/auth-layout/auth-layout.component';
import { SiteLayoutComponent } from './shared/layouts/site-layout/site-layout.component';
import { RegisterPageComponent } from './register-page/register-page.component';
import { WelcomePageComponent } from './welcome-page/welcome-page.component'
import { ReviewPageComponent } from './review-page/review-page.component';
import { ErrorPageComponent } from './error-page/error-page.component';
import { MessagePageComponent } from './message-page/message-page.component';
import { MessageNewComponent } from './message-new/message-new.component';
import { MessageInComponent } from './message-in/message-in.component';
import { MessageOutComponent } from './message-out/message-out.component';
import { UserPageComponent } from './user-page/user-page.component';

const routes: Routes = [
  {
    path: '', component: AuthLayoutComponent, children: [
      { path: '', redirectTo: '/login', pathMatch: 'full' },
      { path: 'login', component: LoginPageComponent },
      { path: 'register', component: RegisterPageComponent }
    ]
  },

  {
    path: '', component: SiteLayoutComponent, children: [
      { path: 'welcome', component: WelcomePageComponent },
      { path: 'user/:nickname', component: UserPageComponent },
      { path: 'review/:nickname', component: ReviewPageComponent },
      { path: 'error', component: ErrorPageComponent },
      {
        path: 'message', component: MessagePageComponent, children: [
          { path: 'new', component: MessageNewComponent },
          { path: 'in', component: MessageInComponent },
          { path: 'out', component: MessageOutComponent }
        ]
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }