import {Inject, Injectable} from '@angular/core';
import {NewUser, User} from './interfaces';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
@Injectable({providedIn: 'root'})

export class AuthService {

    constructor( private http: HttpClient) {   }

    login(user: User): Observable<{token: string}>{
        const sendParams = new HttpParams()
            .append('j_username', user.j_username.toString())
            .append('j_password', user.j_password.toString());

        return this.http.post<{token: string}>('j_security_check', sendParams)
    }

    register(user: NewUser): Observable<{token: string}> {
        const sendParams = new HttpParams()
            .append('nickname', user.nickname.toString())
            .append('email', user.email.toString())
            .append('password', user.password.toString());
        return this.http.post<{token: string}>('api/user/new', sendParams, {headers: {'Content-Type': 'application/x-www-form-urlencoded'}})
    }


}
