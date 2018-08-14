import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IStarkeUserTypesMySuffix } from 'app/shared/model/starke-user-types-my-suffix.model';

type EntityResponseType = HttpResponse<IStarkeUserTypesMySuffix>;
type EntityArrayResponseType = HttpResponse<IStarkeUserTypesMySuffix[]>;

@Injectable({ providedIn: 'root' })
export class StarkeUserTypesMySuffixService {
    private resourceUrl = SERVER_API_URL + 'api/starke-user-types';

    constructor(private http: HttpClient) {}

    create(starkeUserTypes: IStarkeUserTypesMySuffix): Observable<EntityResponseType> {
        return this.http.post<IStarkeUserTypesMySuffix>(this.resourceUrl, starkeUserTypes, { observe: 'response' });
    }

    update(starkeUserTypes: IStarkeUserTypesMySuffix): Observable<EntityResponseType> {
        return this.http.put<IStarkeUserTypesMySuffix>(this.resourceUrl, starkeUserTypes, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IStarkeUserTypesMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IStarkeUserTypesMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
