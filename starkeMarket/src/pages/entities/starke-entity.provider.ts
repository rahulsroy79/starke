import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Api } from '../../providers/api/api';
// todo: handle dates

import { StarkeEntity } from './starke-entity.model';

@Injectable()
export class StarkeEntityService {
    private resourceUrl = Api.API_URL + '/starke-entities';
    private resourceUrl2 = Api.API_URL + '/starke-entities/byparentid';
    constructor(private http: HttpClient) { }

    create(starkeEntity: StarkeEntity): Observable<StarkeEntity> {
        return this.http.post(this.resourceUrl, starkeEntity);
    }

    update(starkeEntity: StarkeEntity): Observable<StarkeEntity> {
        return this.http.put(this.resourceUrl, starkeEntity);
    }

    find(id: number): Observable<StarkeEntity> {
        return this.http.get(`${this.resourceUrl}/${id}`);
    }

    query(req?: any): Observable<any> {
    //query(id: number): Observable<any> {
        return this.http.get(this.resourceUrl);
    }

    querybyparent(id: number): Observable<any> {
        console.log("Calling by parent");
        // return this.http.get(this.resourceUrl);
        return this.http.get(`${this.resourceUrl2}/${id}`);
    }

    delete(id: number): Observable<any> {
        return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response', responseType: 'text' });
    }
}
