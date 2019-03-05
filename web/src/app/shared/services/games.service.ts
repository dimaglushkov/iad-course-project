import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Games } from '../../interfaces';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: "root"
})

export class GameService {
    constructor(private http: HttpClient){

    }

    fetch(): Observable<Games[]> {
       return this.http.get<Games[]>('/api/games') //вписать api списка игр
    }
}