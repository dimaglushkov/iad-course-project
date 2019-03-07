import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';
import { GameResponse, ResponseState } from 'src/app/interfaces';

@Injectable({
    providedIn: "root"
})
export class GameService {

    constructor(private http: HttpClient) {
    }

    getAllGames() {
        return this.http.get<GameResponse>('api/game/all');
    }

    addToLib(id){
        return this.http.get<ResponseState>('api/game/add/' + id);
    }

}