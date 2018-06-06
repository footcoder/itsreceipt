import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Router} from "@angular/router";

/*const httpOptions = {
 headers: new HttpHeaders({'Content-Type': 'application/json'})
 }*/

@Injectable()
export class UserService {

  authenticated = false;
  token:string;

  constructor(private  http: HttpClient, private router:Router) {
  }

  signIn(data) {
    let options = {
      headers: new HttpHeaders().set("Content-Type", "application/x-www-form-urlencoded")
    }
    // headers.append("Authorization", "Basic " + btoa(data.username + ":" + data.password));

    this.http.post('http://footcoder.niee.kr:8080/user/sign-in', "email="+ data.email +"&password="+ data.password, options)
      .subscribe(
        (response:any) => {
          if(response.status == 'T'){
            this.authenticated = true;
            this.token = response.results.token;
            this.router.navigateByUrl('main');
          }else if(response.status == 'F'){
            alert(response.result.message);
          }
        },
        err => console.log(err),
        () => console.log('sign-in finished')
      );
    // let headers = new HttpHeaders();
    // headers.append("Authorization", "Basic " + btoa(data.username + ":" + data.password));
    // this.http.get(
    //   'http://footcoder.niee.kr:8080/user/sign-in',
    //   {headers: headers})
    //   .subscribe(
    //     result => alert(result),
    //     err => console.log(err),
    //     () => console.log('sign-in finished')
    //   );
  }

  signup(data) {
    this.http.post('http://footcoder.niee.kr:8080/user/sign-up', data).subscribe(
      (response:any) => {
        if(response.status == 'T'){
          this.router.navigateByUrl('sign-in');
        }else if(response.status == 'F'){
          alert(response.result.message);
        }
      }, err => {
        console.log(err)
      }, () => console.log('signup finish')
    );;
  }
}
