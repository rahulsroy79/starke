import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { StarkeUsersMySuffix } from 'app/shared/model/starke-users-my-suffix.model';
import { StarkeUsersMySuffixService } from './starke-users-my-suffix.service';
import { StarkeUsersMySuffixComponent } from './starke-users-my-suffix.component';
import { StarkeUsersMySuffixDetailComponent } from './starke-users-my-suffix-detail.component';
import { StarkeUsersMySuffixUpdateComponent } from './starke-users-my-suffix-update.component';
import { StarkeUsersMySuffixDeletePopupComponent } from './starke-users-my-suffix-delete-dialog.component';
import { IStarkeUsersMySuffix } from 'app/shared/model/starke-users-my-suffix.model';

@Injectable({ providedIn: 'root' })
export class StarkeUsersMySuffixResolve implements Resolve<IStarkeUsersMySuffix> {
    constructor(private service: StarkeUsersMySuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((starkeUsers: HttpResponse<StarkeUsersMySuffix>) => starkeUsers.body));
        }
        return of(new StarkeUsersMySuffix());
    }
}

export const starkeUsersRoute: Routes = [
    {
        path: 'starke-users-my-suffix',
        component: StarkeUsersMySuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'StarkeUsers'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'starke-users-my-suffix/:id/view',
        component: StarkeUsersMySuffixDetailComponent,
        resolve: {
            starkeUsers: StarkeUsersMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'StarkeUsers'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'starke-users-my-suffix/new',
        component: StarkeUsersMySuffixUpdateComponent,
        resolve: {
            starkeUsers: StarkeUsersMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'StarkeUsers'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'starke-users-my-suffix/:id/edit',
        component: StarkeUsersMySuffixUpdateComponent,
        resolve: {
            starkeUsers: StarkeUsersMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'StarkeUsers'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const starkeUsersPopupRoute: Routes = [
    {
        path: 'starke-users-my-suffix/:id/delete',
        component: StarkeUsersMySuffixDeletePopupComponent,
        resolve: {
            starkeUsers: StarkeUsersMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'StarkeUsers'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
