import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { EntityPageDetailsMySuffix } from 'app/shared/model/entity-page-details-my-suffix.model';
import { EntityPageDetailsMySuffixService } from './entity-page-details-my-suffix.service';
import { EntityPageDetailsMySuffixComponent } from './entity-page-details-my-suffix.component';
import { EntityPageDetailsMySuffixDetailComponent } from './entity-page-details-my-suffix-detail.component';
import { EntityPageDetailsMySuffixUpdateComponent } from './entity-page-details-my-suffix-update.component';
import { EntityPageDetailsMySuffixDeletePopupComponent } from './entity-page-details-my-suffix-delete-dialog.component';
import { IEntityPageDetailsMySuffix } from 'app/shared/model/entity-page-details-my-suffix.model';

@Injectable({ providedIn: 'root' })
export class EntityPageDetailsMySuffixResolve implements Resolve<IEntityPageDetailsMySuffix> {
    constructor(private service: EntityPageDetailsMySuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((entityPageDetails: HttpResponse<EntityPageDetailsMySuffix>) => entityPageDetails.body));
        }
        return of(new EntityPageDetailsMySuffix());
    }
}

export const entityPageDetailsRoute: Routes = [
    {
        path: 'entity-page-details-my-suffix',
        component: EntityPageDetailsMySuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'EntityPageDetails'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'entity-page-details-my-suffix/:id/view',
        component: EntityPageDetailsMySuffixDetailComponent,
        resolve: {
            entityPageDetails: EntityPageDetailsMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'EntityPageDetails'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'entity-page-details-my-suffix/new',
        component: EntityPageDetailsMySuffixUpdateComponent,
        resolve: {
            entityPageDetails: EntityPageDetailsMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'EntityPageDetails'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'entity-page-details-my-suffix/:id/edit',
        component: EntityPageDetailsMySuffixUpdateComponent,
        resolve: {
            entityPageDetails: EntityPageDetailsMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'EntityPageDetails'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const entityPageDetailsPopupRoute: Routes = [
    {
        path: 'entity-page-details-my-suffix/:id/delete',
        component: EntityPageDetailsMySuffixDeletePopupComponent,
        resolve: {
            entityPageDetails: EntityPageDetailsMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'EntityPageDetails'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
