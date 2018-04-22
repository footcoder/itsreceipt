import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {headersToString} from "selenium-webdriver/http";

/*const httpOptions = {
 headers: new HttpHeaders({'Content-Type': 'application/json'})
 }*/

@Injectable()
export class UserService {

  authenticated = false;

  constructor(private  http: HttpClient) {
  }

  signIn(data) {
    let headers = new HttpHeaders();
    headers.append("Authorization", "Basic " + btoa(data.username + ":" + data.password));
    this.http.get(
      'http://footcoder.niee.kr:8080/user/sign-in',
      {headers: headers})
      .subscribe(
        result => alert(result),
        err => console.log(err),
        () => console.log('sign-in finished')
      );
  }

  signup(data) {
    return this.http.post('http://footcoder.niee.kr:8080/user/sign-up', data);
  }
}
