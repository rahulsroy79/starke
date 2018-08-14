/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { Starkev2TestModule } from '../../../test.module';
import { EntityDetailsMySuffixComponent } from 'app/entities/entity-details-my-suffix/entity-details-my-suffix.component';
import { EntityDetailsMySuffixService } from 'app/entities/entity-details-my-suffix/entity-details-my-suffix.service';
import { EntityDetailsMySuffix } from 'app/shared/model/entity-details-my-suffix.model';

describe('Component Tests', () => {
    describe('EntityDetailsMySuffix Management Component', () => {
        let comp: EntityDetailsMySuffixComponent;
        let fixture: ComponentFixture<EntityDetailsMySuffixComponent>;
        let service: EntityDetailsMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Starkev2TestModule],
                declarations: [EntityDetailsMySuffixComponent],
                providers: []
            })
                .overrideTemplate(EntityDetailsMySuffixComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EntityDetailsMySuffixComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EntityDetailsMySuffixService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new EntityDetailsMySuffix(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.entityDetails[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
