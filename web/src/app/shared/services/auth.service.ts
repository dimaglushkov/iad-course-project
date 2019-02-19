import { Injectable } from '@angular/core';
import { User, NewUser } from './interfaces';
import { HttpClient } from '@angular/common/http';

@Injectable({providedIn: 'root'})

export class AuthService {

    constructor( private http: HttpClient) {

    }

    register(user: NewUser) {
        
        return this.http.post<NewUser>('j_security_check', user)
    }

    login(user: User) {
        return this.http.post<User>('j_security_check', user)
    }
}

