import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { EntityPageFormatsMySuffix } from 'app/shared/model/entity-page-formats-my-suffix.model';
import { EntityPageFormatsMySuffixService } from './entity-page-formats-my-suffix.service';
import { EntityPageFormatsMySuffixComponent } from './entity-page-formats-my-suffix.component';
import { EntityPageFormatsMySuffixDetailComponent } from './entity-page-formats-my-suffix-detail.component';
import { EntityPageFormatsMySuffixUpdateComponent } from './entity-page-formats-my-suffix-update.component';
import { EntityPageFormatsMySuffixDeletePopupComponent } from './entity-page-formats-my-suffix-delete-dialog.component';
import { IEntityPageFormatsMySuffix } from 'app/shared/model/entity-page-formats-my-suffix.model';

@Injectable({ providedIn: 'root' })
export class EntityPageFormatsMySuffixResolve implements Resolve<IEntityPageFormatsMySuffix> {
    constructor(private service: EntityPageFormatsMySuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((entityPageFormats: HttpResponse<EntityPageFormatsMySuffix>) => entityPageFormats.body));
        }
        return of(new EntityPageFormatsMySuffix());
    }
}

export const entityPageFormatsRoute: Routes = [
    {
        path: 'entity-page-formats-my-suffix',
        component: EntityPageFormatsMySuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'EntityPageFormats'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'entity-page-formats-my-suffix/:id/view',
        component: EntityPageFormatsMySuffixDetailComponent,
        resolve: {
            entityPageFormats: EntityPageFormatsMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'EntityPageFormats'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'entity-page-formats-my-suffix/new',
        component: EntityPageFormatsMySuffixUpdateComponent,
        resolve: {
            entityPageFormats: EntityPageFormatsMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'EntityPageFormats'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'entity-page-formats-my-suffix/:id/edit',
        component: EntityPageFormatsMySuffixUpdateComponent,
        resolve: {
            entityPageFormats: EntityPageFormatsMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'EntityPageFormats'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const entityPageFormatsPopupRoute: Routes = [
    {
        path: 'entity-page-formats-my-suffix/:id/delete',
        component: EntityPageFormatsMySuffixDeletePopupComponent,
        resolve: {
            entityPageFormats: EntityPageFormatsMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'EntityPageFormats'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
