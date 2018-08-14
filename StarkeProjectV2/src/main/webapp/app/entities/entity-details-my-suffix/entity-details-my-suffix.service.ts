import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEntityDetailsMySuffix } from 'app/shared/model/entity-details-my-suffix.model';

type EntityResponseType = HttpResponse<IEntityDetailsMySuffix>;
type EntityArrayResponseType = HttpResponse<IEntityDetailsMySuffix[]>;

@Injectable({ providedIn: 'root' })
export class EntityDetailsMySuffixService {
    private resourceUrl = SERVER_API_URL + 'api/entity-details';

    constructor(private http: HttpClient) {}

    create(entityDetails: IEntityDetailsMySuffix): Observable<EntityResponseType> {
        return this.http.post<IEntityDetailsMySuffix>(this.resourceUrl, entityDetails, { observe: 'response' });
    }

    update(entityDetails: IEntityDetailsMySuffix): Observable<EntityResponseType> {
        return this.http.put<IEntityDetailsMySuffix>(this.resourceUrl, entityDetails, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IEntityDetailsMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IEntityDetailsMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
