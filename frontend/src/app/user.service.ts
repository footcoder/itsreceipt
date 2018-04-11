import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
}

@Injectable()
export class UserService {

  authenticated = false;

  constructor(private  http: HttpClient) {
  }

  signIn(data) {
    let headers: HttpHeaders = new HttpHeaders({
      contentType: "application/x-www-form-urlencoded"
    });

    return this.http.post('http://footcoder.niee.kr:8080/user/sign-in', data, {headers: headers});

    // let username: string = data.email;
    // let password: string = data.passwordError;
    // let headers: Headers = new Headers();
    // headers.append("Authorization", "Basic " + btoa(username + ":" + password));
    // headers.append("Content-Type", "application/x-www-form-urlencoded");
    // return this.http.post('http://footcoder.niee.kr:8080/user/sign-in', headers);
  }

  signup(data) {
    return this.http.post('http://footcoder.niee.kr:8080/user/sign-up', data);
  }
}
