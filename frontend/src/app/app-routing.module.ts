import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {SignUpComponent} from "./sign-up/sign-up.component";
import {SignInComponent} from "./sign-in/sign-in.component";
import {ViewComponent} from "./view/view.component";
import {WriteComponent} from "./write/write.component";
import {MainComponent} from "./main/main.component";

const routes: Routes = [
  { path: '', redirectTo: '/main', pathMatch: 'full' },
  { path:'main', component: MainComponent},
  { path:'sign-in', component: SignInComponent},
  { path:'sign-up', component: SignUpComponent},
  { path:'write', component: WriteComponent},
  { path:'view/:id', component: ViewComponent}
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
