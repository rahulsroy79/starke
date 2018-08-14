import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IStarkeEntityMySuffix } from 'app/shared/model/starke-entity-my-suffix.model';

type EntityResponseType = HttpResponse<IStarkeEntityMySuffix>;
type EntityArrayResponseType = HttpResponse<IStarkeEntityMySuffix[]>;

@Injectable({ providedIn: 'root' })
export class StarkeEntityMySuffixService {
    private resourceUrl = SERVER_API_URL + 'api/starke-entities';

    constructor(private http: HttpClient) {}

    create(starkeEntity: IStarkeEntityMySuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(starkeEntity);
        return this.http
            .post<IStarkeEntityMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(starkeEntity: IStarkeEntityMySuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(starkeEntity);
        return this.http
            .put<IStarkeEntityMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IStarkeEntityMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IStarkeEntityMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(starkeEntity: IStarkeEntityMySuffix): IStarkeEntityMySuffix {
        const copy: IStarkeEntityMySuffix = Object.assign({}, starkeEntity, {
            entitycreationdate:
                starkeEntity.entitycreationdate != null && starkeEntity.entitycreationdate.isValid()
                    ? starkeEntity.entitycreationdate.format(DATE_FORMAT)
                    : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.entitycreationdate = res.body.entitycreationdate != null ? moment(res.body.entitycreationdate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((starkeEntity: IStarkeEntityMySuffix) => {
            starkeEntity.entitycreationdate = starkeEntity.entitycreationdate != null ? moment(starkeEntity.entitycreationdate) : null;
        });
        return res;
    }
}
