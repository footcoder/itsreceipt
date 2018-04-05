import {Component, OnInit, ViewChild, ElementRef} from '@angular/core';
import {UserService} from '../user.service';
import * as $ from 'jquery';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent implements OnInit {

  @ViewChild('email', {read: ElementRef}) email:ElementRef;
  @ViewChild('password', {read: ElementRef}) password:ElementRef;
  emailErrorMessage:string;
  passwordErrorMessage:string;
  emailError:boolean;
  passwordError:boolean;

  constructor(private userService:UserService) { }

  ngOnInit() {
  }

  signIn(){
    this.emailError = !this.email.nativeElement.validity.valid;
    this.passwordError = !this.password.nativeElement.validity.valid;
    this.emailErrorMessage = this.email.nativeElement.validationMessage;
    this.passwordErrorMessage = this.password.nativeElement.validationMessage;

    //TODO: 비번 RSA 암호화 필요

    if(!this.emailError && !this.passwordError){
      this.userService.signIn({email: this.email.nativeElement.value, password : this.password.nativeElement.value}).subscribe(
        result => console.log(result),
        error => console.log(error),
        () => console.log('signin complate')
      );
    }
  }
}
