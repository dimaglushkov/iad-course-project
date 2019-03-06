import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { PersonResponse } from 'src/app/interfaces';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  getUser(nickname)
  {
    return this.http.get<PersonResponse>("api/user/" + nickname);
  }



}
