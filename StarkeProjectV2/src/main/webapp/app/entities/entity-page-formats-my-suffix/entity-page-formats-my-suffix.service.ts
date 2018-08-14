import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEntityPageFormatsMySuffix } from 'app/shared/model/entity-page-formats-my-suffix.model';

type EntityResponseType = HttpResponse<IEntityPageFormatsMySuffix>;
type EntityArrayResponseType = HttpResponse<IEntityPageFormatsMySuffix[]>;

@Injectable({ providedIn: 'root' })
export class EntityPageFormatsMySuffixService {
    private resourceUrl = SERVER_API_URL + 'api/entity-page-formats';

    constructor(private http: HttpClient) {}

    create(entityPageFormats: IEntityPageFormatsMySuffix): Observable<EntityResponseType> {
        return this.http.post<IEntityPageFormatsMySuffix>(this.resourceUrl, entityPageFormats, { observe: 'response' });
    }

    update(entityPageFormats: IEntityPageFormatsMySuffix): Observable<EntityResponseType> {
        return this.http.put<IEntityPageFormatsMySuffix>(this.resourceUrl, entityPageFormats, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IEntityPageFormatsMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IEntityPageFormatsMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
