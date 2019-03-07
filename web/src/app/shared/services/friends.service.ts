import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FriendResponse, ResponseState } from 'src/app/interfaces';


@Injectable({
  providedIn: 'root'
})
export class FriendsService {

  constructor( private http: HttpClient) {}


  getFriends(nickname){
    return this.http.get<FriendResponse>('api/friend/' + nickname);
  }

  getRequests(){
    return this.http.get<FriendResponse>('api/friend/requests');
  }

  addFriend(nickname){
    return this.http.get<ResponseState>('api/friend/new/' + nickname);
  }

  acceptRequest(nickname){
    return this.http.post<ResponseState>('api/friend/accept/' + nickname, null);
  }

  declineRequest(nickname){
    return this.http.post<ResponseState>('api/friend/decline/' + nickname, null);
  }

  removeFriend(nickname){
    return this.http.post<ResponseState>('api/friend/delete' + nickname, null);
  }
  
}
