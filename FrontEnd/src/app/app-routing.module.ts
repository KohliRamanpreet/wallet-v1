import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {loginComponent} from './login/login.component';
import {SignupComponent} from './signup/signup.component';
import {SuccessPageComponent} from './successPage/successPage.component';
import { ProfileComponent } from './profile/profile.component';
import { TransactionComponent } from './transaction/transaction.component';
import { PrintComponent } from './printtransaction/printtransaction.component';
import { AuthGuard } from './auth.guard';



const routes: Routes = [
  {path: '', component: loginComponent},
  {path: 'signup', component: SignupComponent},
  {path: 'successpage', component: SuccessPageComponent,canActivate:[AuthGuard]},
  {path:'successpage/profile', component:ProfileComponent,canActivate:[AuthGuard]},
  {path:'transaction', component:TransactionComponent,canActivate:[AuthGuard]},
  {path: 'print', component:PrintComponent,canActivate:[AuthGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents=[loginComponent,PrintComponent,TransactionComponent,SignupComponent,SuccessPageComponent,ProfileComponent]
