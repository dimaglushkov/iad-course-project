import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserInfo, User } from '../../interfaces';
import { Observable } from 'rxjs';

@Injectable({
    providedIn:'root'
})

export class UserService {

    constructor(private http: HttpClient) { }

    getUserInfo(): Observable<UserInfo[]> {
       return this.http.get<UserInfo[]>('api/user/profile-page')
    }
}