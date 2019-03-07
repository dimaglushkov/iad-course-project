import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { RightsCheck, ResponseState } from 'src/app/interfaces';

@Injectable({
  providedIn: 'root'
})
export class SettingsService {

  constructor(private http: HttpClient) { }

  checkRights() {
    return this.http.get<RightsCheck>('api/access/check');
  }

  grantRigths(nickname) {
    return this.http.post<ResponseState>('api/access/' + nickname + '/grant/admin', null);
  }

  ungrantRigths(nickname) {
    return this.http.post<ResponseState>('api/access/' + nickname + '/ungrant/admin', null);
  }

  ban(nickname) {
    return this.http.post<ResponseState>('api/access/' + nickname + '/ban', null);
  }

  unban(nickname) {
    return this.http.post<ResponseState>('api/access/' + nickname + '/unban', null);
  }

  setInfo(name, surname, country, city, birthDate) {
    var params = new HttpParams()
      .append('name', name)
      .append('surname', surname)
      .append('country', country)
      .append('city', city)
      .append('birthDate', birthDate);

    return this.http.post<ResponseState>('api/info/set', params, { headers: { 'Content-Type': 'application/x-www-form-urlencoded' } })
  }

  addGame(gameName, gameDate, gameDesc) {
    const params = new HttpParams()
      .append('name', gameName)
      .append('date', gameDate)
      .append('description', gameDesc);

    return this.http.post<ResponseState>('api/game/new', params, { headers: { 'Content-Type': 'application/x-www-form-urlencoded' } })
  }

}
