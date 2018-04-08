import {Component, OnInit, ViewChild, ElementRef} from '@angular/core';
import {UserService} from "../user.service";

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {

  @ViewChild('email') email: ElementRef;
  @ViewChild('password') password: ElementRef;
  @ViewChild('password_check') passwordCheck: ElementRef;
  @ViewChild('korea') korea: ElementRef;
  @ViewChild('usd') usd: ElementRef;

  emailErrorMessage: string;
  passwordErrorMessage: string;
  passwordCheckErrorMessage: string;
  typeErrorMessage: string;
  emailError: boolean;
  passwordError: boolean;
  passwordCheckError: boolean;
  typeError: boolean;

  constructor(private userService: UserService) {
  }

  ngOnInit() {
  }

  signup() {
    this.emailError = !this.email.nativeElement.validity.valid;
    this.passwordError = !this.password.nativeElement.validity.valid;
    this.passwordCheckError = !this.passwordCheck.nativeElement.validity.valid;
    this.typeError = !this.korea.nativeElement.checked && !this.usd.nativeElement.checked;

    this.emailErrorMessage = this.email.nativeElement.validationMessage;
    this.passwordErrorMessage = this.password.nativeElement.validationMessage;
    this.passwordCheckErrorMessage = this.passwordCheck.nativeElement.validationMessage;
    if (this.typeError) {
      this.typeErrorMessage = 'DO NOT SELECT MONEY TYPE';
    } else {

    }

    if (!this.emailError && !this.passwordError && !this.typeError) {
      let data = {
        email: this.email.nativeElement.value,
        password: this.password.nativeElement.value,
        moneyType: this.korea.nativeElement.checked ? 1 : 2
      }
      this.userService.signup(data).subscribe(
        result => {
          console.log(result)
        }, err => {
          console.log(err)
        }, () => console.log('signup finish')
      );
    }
  }

}
