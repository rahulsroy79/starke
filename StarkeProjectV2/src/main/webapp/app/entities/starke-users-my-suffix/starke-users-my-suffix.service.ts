import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IStarkeUsersMySuffix } from 'app/shared/model/starke-users-my-suffix.model';

type EntityResponseType = HttpResponse<IStarkeUsersMySuffix>;
type EntityArrayResponseType = HttpResponse<IStarkeUsersMySuffix[]>;

@Injectable({ providedIn: 'root' })
export class StarkeUsersMySuffixService {
    private resourceUrl = SERVER_API_URL + 'api/starke-users';

    constructor(private http: HttpClient) {}

    create(starkeUsers: IStarkeUsersMySuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(starkeUsers);
        return this.http
            .post<IStarkeUsersMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(starkeUsers: IStarkeUsersMySuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(starkeUsers);
        return this.http
            .put<IStarkeUsersMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IStarkeUsersMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IStarkeUsersMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(starkeUsers: IStarkeUsersMySuffix): IStarkeUsersMySuffix {
        const copy: IStarkeUsersMySuffix = Object.assign({}, starkeUsers, {
            activesince:
                starkeUsers.activesince != null && starkeUsers.activesince.isValid() ? starkeUsers.activesince.format(DATE_FORMAT) : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.activesince = res.body.activesince != null ? moment(res.body.activesince) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((starkeUsers: IStarkeUsersMySuffix) => {
            starkeUsers.activesince = starkeUsers.activesince != null ? moment(starkeUsers.activesince) : null;
        });
        return res;
    }
}
