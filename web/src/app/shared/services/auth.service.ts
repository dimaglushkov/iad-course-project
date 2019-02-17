import { Injectable } from '@angular/core';
import { User, NewUser } from './interfaces';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({providedIn: 'root'})

export class AuthService {

    constructor( private http: HttpClient) {

    }

    register(user: NewUser): Observable<{token: string}> {
        return this.http.post<{token: string}>('api/registration/new', user)
    }

    login(user: User): Observable<{token: string}> {
        return this.http.post<{token: string}>('j_security_check', user)
    }
}