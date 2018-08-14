import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEntityReviewsMySuffix } from 'app/shared/model/entity-reviews-my-suffix.model';

type EntityResponseType = HttpResponse<IEntityReviewsMySuffix>;
type EntityArrayResponseType = HttpResponse<IEntityReviewsMySuffix[]>;

@Injectable({ providedIn: 'root' })
export class EntityReviewsMySuffixService {
    private resourceUrl = SERVER_API_URL + 'api/entity-reviews';

    constructor(private http: HttpClient) {}

    create(entityReviews: IEntityReviewsMySuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(entityReviews);
        return this.http
            .post<IEntityReviewsMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(entityReviews: IEntityReviewsMySuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(entityReviews);
        return this.http
            .put<IEntityReviewsMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IEntityReviewsMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IEntityReviewsMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(entityReviews: IEntityReviewsMySuffix): IEntityReviewsMySuffix {
        const copy: IEntityReviewsMySuffix = Object.assign({}, entityReviews, {
            reviewdate:
                entityReviews.reviewdate != null && entityReviews.reviewdate.isValid() ? entityReviews.reviewdate.format(DATE_FORMAT) : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.reviewdate = res.body.reviewdate != null ? moment(res.body.reviewdate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((entityReviews: IEntityReviewsMySuffix) => {
            entityReviews.reviewdate = entityReviews.reviewdate != null ? moment(entityReviews.reviewdate) : null;
        });
        return res;
    }
}
