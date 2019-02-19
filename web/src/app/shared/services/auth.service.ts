<<<<<<< HEAD
import { Injectable } from '@angular/core';
import { User, NewUser } from './interfaces';
import { HttpClient } from '@angular/common/http';
=======
import {Injectable} from '@angular/core';
import {NewUser, User} from './interfaces';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';

>>>>>>> f32cc7ac1efeabb24bda55a48a62864fdf0b8729

@Injectable({providedIn: 'root'})

export class AuthService {

    constructor( private http: HttpClient) {

    }

<<<<<<< HEAD
    register(user: NewUser) {
        
        return this.http.post<NewUser>('j_security_check', user)
    }

    login(user: User) {
        return this.http.post<User>('j_security_check', user)
    }
}

=======
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
        return this.http.post<{token: string}>('api/registration/new', sendParams, {headers: {'Content-Type': 'application/x-www-form-urlencoded'}})
    }


}
>>>>>>> f32cc7ac1efeabb24bda55a48a62864fdf0b8729
