import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { ResponseState } from 'src/app/interfaces';

@Injectable({
  providedIn: 'root'
})
export class MessageService {
  
  constructor(private http: HttpClient) { }

  sendMessage(receiver:string, topic:string, text:string){
    const params = new HttpParams()
            .append('receiver', receiver)
            .append('topic', topic)
            .append('text', text);

    return this.http.post<ResponseState>('api/message/send', params, {headers: {'Content-Type': 'application/x-www-form-urlencoded'}});
  }

}
