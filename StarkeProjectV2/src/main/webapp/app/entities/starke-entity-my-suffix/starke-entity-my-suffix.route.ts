import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { StarkeEntityMySuffix } from 'app/shared/model/starke-entity-my-suffix.model';
import { StarkeEntityMySuffixService } from './starke-entity-my-suffix.service';
import { StarkeEntityMySuffixComponent } from './starke-entity-my-suffix.component';
import { StarkeEntityMySuffixDetailComponent } from './starke-entity-my-suffix-detail.component';
import { StarkeEntityMySuffixUpdateComponent } from './starke-entity-my-suffix-update.component';
import { StarkeEntityMySuffixDeletePopupComponent } from './starke-entity-my-suffix-delete-dialog.component';
import { IStarkeEntityMySuffix } from 'app/shared/model/starke-entity-my-suffix.model';

@Injectable({ providedIn: 'root' })
export class StarkeEntityMySuffixResolve implements Resolve<IStarkeEntityMySuffix> {
    constructor(private service: StarkeEntityMySuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((starkeEntity: HttpResponse<StarkeEntityMySuffix>) => starkeEntity.body));
        }
        return of(new StarkeEntityMySuffix());
    }
}

export const starkeEntityRoute: Routes = [
    {
        path: 'starke-entity-my-suffix',
        component: StarkeEntityMySuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'StarkeEntities'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'starke-entity-my-suffix/:id/view',
        component: StarkeEntityMySuffixDetailComponent,
        resolve: {
            starkeEntity: StarkeEntityMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'StarkeEntities'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'starke-entity-my-suffix/new',
        component: StarkeEntityMySuffixUpdateComponent,
        resolve: {
            starkeEntity: StarkeEntityMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'StarkeEntities'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'starke-entity-my-suffix/:id/edit',
        component: StarkeEntityMySuffixUpdateComponent,
        resolve: {
            starkeEntity: StarkeEntityMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'StarkeEntities'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const starkeEntityPopupRoute: Routes = [
    {
        path: 'starke-entity-my-suffix/:id/delete',
        component: StarkeEntityMySuffixDeletePopupComponent,
        resolve: {
            starkeEntity: StarkeEntityMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'StarkeEntities'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
