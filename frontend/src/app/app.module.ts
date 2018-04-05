import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HttpClientJsonpModule } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';


import { AppComponent } from './app.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { AppRoutingModule } from './app-routing.module';
import { SignInComponent } from './sign-in/sign-in.component';
import { MessageComponent } from './message/message.component';
import { UserService } from './user.service';

@NgModule({
  declarations: [
    AppComponent,
    SignUpComponent,
    SignInComponent,
    MessageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    HttpClientJsonpModule
  ],
  providers: [
    UserService, FormBuilder
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
