import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {loginComponent} from './login/login.component';
import {SignupComponent} from './signup/signup.component';
import {SuccessPageComponent} from './successPage/successPage.component';
import {DoNotExistComponent} from './donotexist/donotexist.component'
import { ProfileComponent } from './profile/profile.component';
import {BalanceComponent} from './balance/balance.component'



const routes: Routes = [
  {path: '', component: loginComponent},
  {path: 'signup', component: SignupComponent},
  {path: 'donotexist', component: DoNotExistComponent},
  {path: 'successpage', component: SuccessPageComponent},
  {path:'successpage/profile', component:ProfileComponent},
  {path: 'balance', component:BalanceComponent}
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents=[loginComponent,SignupComponent,DoNotExistComponent,SuccessPageComponent,ProfileComponent,BalanceComponent]
