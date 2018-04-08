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

  signIn(data, callback) {
    const headers = new HttpHeaders(data ? {
        authorization: 'Basic ' + btoa(data.email + ':' + data.password)
      } : {});

    this.http.post('http://footcoder.niee.kr:8080/user/sign-in', {headers: headers})
      .subscribe(
        response => {
          if (response['email']) {
            this.authenticated = true;
          } else {
            this.authenticated = false;
          }
          return callback && callback();
        },
        err =>{
          cnosole.log(err);
        },
        () => console.log('sign-in finished')
      );

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
