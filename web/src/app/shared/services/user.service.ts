import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  getUser(nickname)
  {
    //TODO: change links to relative
    return this.http.get("http://localhost:5080/iad/api/user/" + nickname);
  }

}
