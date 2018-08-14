/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { Starkev2TestModule } from '../../../test.module';
import { EntityPageDetailsMySuffixComponent } from 'app/entities/entity-page-details-my-suffix/entity-page-details-my-suffix.component';
import { EntityPageDetailsMySuffixService } from 'app/entities/entity-page-details-my-suffix/entity-page-details-my-suffix.service';
import { EntityPageDetailsMySuffix } from 'app/shared/model/entity-page-details-my-suffix.model';

describe('Component Tests', () => {
    describe('EntityPageDetailsMySuffix Management Component', () => {
        let comp: EntityPageDetailsMySuffixComponent;
        let fixture: ComponentFixture<EntityPageDetailsMySuffixComponent>;
        let service: EntityPageDetailsMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Starkev2TestModule],
                declarations: [EntityPageDetailsMySuffixComponent],
                providers: []
            })
                .overrideTemplate(EntityPageDetailsMySuffixComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EntityPageDetailsMySuffixComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EntityPageDetailsMySuffixService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new EntityPageDetailsMySuffix(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.entityPageDetails[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
